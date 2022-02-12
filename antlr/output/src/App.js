import { Grommet, Grid, Box } from 'grommet'
function App() {
	return (
		<Grommet>
			<Grid
				rows={['small','medium','large','small',]}
				columns={['large','small','medium','xsmall',]}
				gap='xxsmall'
				areas={[
					{ name: 'first', start: [0, 0], end: [1, 2] },
					{ name: 'second', start: [2, 2], end: [3, 3] },
				]}
			>
				<Box gridArea='first' background='RED' />
				<Box gridArea='second' background='BLUE' />
			</Grid>
		</Grommet>
	);
}
export default App;
