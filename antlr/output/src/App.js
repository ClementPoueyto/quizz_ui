import React, { Component } from 'react'
import { } from './functions'
import { Grommet, Grid, Box, CheckBoxGroup, Text, Button, Clock, ResponsiveContext, TextInput } from 'grommet'
import { deepMerge } from "grommet/utils";
import { grommet } from "grommet/themes";
var data = require('./quiz.json');

export default class App extends Component {

	constructor() {
		super();
		this.state = { quiz : data }
	}

	render() {
		var customBreakpoints = deepMerge(grommet, {
			global: {
<<<<<<< HEAD
=======
				font: {
					family: 'SCRIPT'
				}
>>>>>>> 06188f9c3a9c1630551effcb96ccb2cf628a23f1
			}
		});
		var c_areas= []
		const areas = {
			default: [
<<<<<<< HEAD
				["unTruc",],
			],
		}
		const rows = {
			default:['medium','large',],
		}
		const columns = {
			default:['large',],
=======
				["nav","middle","right",],
				["footer","footer","footer",],
			],
		}
		const rows = {
			default:['large','small',],
		}
		const columns = {
			default:['medium','large','medium',],
>>>>>>> 06188f9c3a9c1630551effcb96ccb2cf628a23f1
}
		return (
			<Grommet>
				<ResponsiveContext.Consumer>
					{size =>
					<Grid
						rows={rows[size] ? rows[size] : rows["default"]}
						columns={columns[size] ? columns[size] : columns["default"]}
					gap='null'
						areas={areas[size] ? areas[size] : areas["default"]}>
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
<<<<<<< HEAD
						c_areas.find((row) => row.indexOf("unTruc") >=0) ?
						<Box gridArea='unTruc' align='center' background='light-3'>
						<Text size='medium'  textAlign='center'  >{this.state.quiz.questions[this.state.quiz.indexQuestion].statement}</Text>
						<TextInput placeholder="Enter your answer here" size="medium" textAlign="center"/>
=======
						c_areas.find((row) => row.indexOf("middle") >=0) ?
						<Box gridArea='middle' align='center' background='light-2'>
						<Text size='medium'  textAlign='center'  >{this.state.quiz.questions[this.state.quiz.indexQuestion].statement}</Text>
						{this.state.quiz.questions[this.state.quiz.indexQuestion].answers.map((item,index)=>{
							return <Button primary={true}  size='small'  margin='small'  color='LIGHT-3'  onClick={()=>{ this.setState({ quiz : onAnswerClick(this.state.quiz,item,index)})}}  label={this.state.quiz.questions[this.state.quiz.indexQuestion].answers[index]}  />
						})}
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("nav") >=0) ?
						<Box gridArea='nav' align='center' background='brand'>
						<Text size='large'  textAlign='center'  >{this.state.quiz.title}</Text>
						<Text size='large'  textAlign='center'  >{this.state.quiz.theme}</Text>
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("right") >=0) ?
						<Box gridArea='right' align='center' background='brand'>
						<Clock run='backward'  type='digital'  size='large'  time='T00:01:00'  alignSelf='center'  precision='seconds'  onChange={(time)=>{ this.setState({ quiz : onTimerChange(this.state.quiz,time)})}}  />
						</Box>
						:
						<Box/>
					}
					{
						c_areas =  areas[size] ? areas[size] : areas["default"],
						c_areas.find((row) => row.indexOf("footer") >=0) ?
						<Box gridArea='footer' align='center' background='light-3'>
>>>>>>> 06188f9c3a9c1630551effcb96ccb2cf628a23f1
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
