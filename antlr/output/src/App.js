import React, { Component } from 'react'
import {  onAnswerClick,  onTimerChange, onMultipleAnswerChange} from './functions'
import { Grommet, Grid, Box, CheckBoxGroup, Text, Button, Clock, Image, ResponsiveContext, TextInput, Meter } from 'grommet'
import { deepMerge } from "grommet/utils";
import { grommet } from "grommet/themes";
var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = { quiz : data }
	}

	renderQuestion(){
		let i = this.state.quiz.indexQuestion;
				return(<>
					<Text size='medium'  textAlign='center'  >{this.state.quiz.questions[i].statement.text}</Text>
					{this.state.quiz.questions[i].answers.map((item,index)=>{
							return <Button primary={true}  size='large'  margin='small'  color='light-2'  onClick={()=>{ this.setState({ quiz : onAnswerClick(this.state.quiz,item,index)})}}  label={this.state.quiz.questions[i].answers[index]}  />
})}
					</>)
	}
	render() {
		var customBreakpoints = deepMerge(grommet, {
			global: {
			}
		});
		var c_areas= []
		const areas = {
			default: [
				["left","header",],
				["middle","middle",],
				["footer","footer",],
			],
		}
		const rows = {
			default:['xsmall','medium','small',],
		}
		const columns = {
			default:['auto','auto',],
}
		const gaps = {
			default:'small',
}
		return (
			<Grommet>
				<ResponsiveContext.Consumer>
					{size =>
					<Grid
						rows={rows[size] ? rows[size] : rows["default"]}
						columns={columns[size] ? columns[size] : columns["default"]}
						gap={gaps[size] ? gaps[size] : gaps["default"]}
						areas={areas[size] ? areas[size] : areas["default"]}>
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("middle") >=0) ?
						<Box overflow='auto' gridArea='middle' align='center'   background='dark-2' round='small' border={{color: "dark-3",size: "small",style: "dashed",}}>
							{this.renderQuestion()}
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("header") >=0) ?
						<Box overflow='auto' gridArea='header' align='center'   background='dark-5' round='medium' >
						<Text size='xlarge'  textAlign='center'  >{this.state.quiz.title}</Text>
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("left") >=0) ?
						<Box overflow='auto' gridArea='left' align='center'   background='light-2' round='medium' >
						<Clock run='backward'  type='digital'  size='large'  time='T00:01:00'  alignSelf='center'  precision='seconds'  onChange={(time)=>{ this.setState({ quiz : onTimerChange(this.state.quiz,time)})}}  />
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("footer") >=0) ?
						<Box overflow='auto' gridArea='footer' align='center'   background='dark-3' round='small' >
						<Meter size='full'  type='bar'  thickness='medium' value = {this.state.quiz.indexQuestion*100/this.state.quiz.questions.length}  />
						</Box>
						:
						<Box/>
					}
				</Grid>
			}
			</ResponsiveContext.Consumer>
		</Grommet>
		);
	}
}
